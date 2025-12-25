# 1️⃣ Кластеры RabbitMQ

## 🔹 Что это такое

**Кластер** = несколько RabbitMQ-нод, работающих как **единый брокер**.

┌─────────┐
│ Node A   │
├─────────┤
│ Node B   │
├─────────┤
│ Node C   │
└─────────┘

- метаданные (exchanges, queues, bindings) — общие
    
- клиенты могут подключаться к **любой ноде**
    

📌 **Если одна нода упала — брокер продолжает работать**

---

## 🔹 Как реализуется

### ▶ Создание кластера (идея)

rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl join_cluster rabbit@node1
rabbitmqctl start_app

В Docker чаще используют **rabbitmq-cluster-operator** или **docker-compose**

---

## 🔹 Что НЕ реплицируется автоматически

|Что|Реплицируется|
|---|---|
|Exchanges|✅|
|Bindings|✅|
|Users|✅|
|**Messages**|❌ (по умолчанию)|

👉 Для сообщений нужны **зеркальные / quorum очереди**

---

# 2️⃣ Зеркалирование очередей (HA Queues / Quorum Queues)

## 🔹 Зачем

Без зеркалирования:

- очередь живёт на **одной ноде**
    
- нода упала → **сообщения потеряны**
    

---

## 🔹 Вариант 1: Mirrored Queues (устаревающие)

rabbitmqctl set_policy ha-all "^q\." \
'{"ha-mode":"all"}'

- master + mirrors
    
- синхронизация слабая
    
- **DEPRECATED**
    

❌ Не рекомендуется для новых проектов

---

## 🔹 Вариант 2: Quorum Queues ✅ (РЕКОМЕНДУЕТСЯ)

Основаны на **Raft consensus**

### ▶ Создание quorum queue

@Bean
public Queue orderQueue() {
    return QueueBuilder
        .durable("q.order")
        .quorum()
        .build();
}

---

## 🔹 Как работает quorum queue

Node A (leader)
Node B (follower)
Node C (follower)

- сообщение считается сохранённым **только если записано на majority**
    
- leader упал → новый leader
    
- **сообщения не теряются**
    

📌 Цена: ниже throughput, но выше надёжность

---

# 3️⃣ Подтверждение доставки (Acknowledgements)

Это **основа отказоустойчивости**.

---

## 🔹 Consumer ACK

@RabbitListener(
    queues = "q.order",
    ackMode = "MANUAL"
)
public void consume(
    OrderEvent event,
    Channel channel,
    @Header(AmqpHeaders.DELIVERY_TAG) long tag
) throws IOException {

    try {
        process(event);
        channel.basicAck(tag, false);
    } catch (Exception e) {
        channel.basicNack(tag, false, true);
    }
}


### Поведение:

|Ситуация|Результат|
|---|---|
|ACK|сообщение удалено|
|NACK + requeue=true|вернётся|
|Consumer умер|вернётся|

---

## 🔹 Producer Confirms (ВАЖНО!)

Гарантия, что сообщение **дошло до брокера**

rabbitTemplate.setConfirmCallback((corr, ack, cause) -> {
    if (!ack) {
        log.error("Message not delivered: {}", cause);
    }
});


📌 Без confirms:

- producer **не знает**, принял ли брокер сообщение
    

---

# 4️⃣ Durable exchange + queue + persistent messages

## 🔹 Конфигурация

new DirectExchange("ex.order", true, false);

QueueBuilder
    .durable("q.order")
    .quorum()
    .build();


---

## 🔹 Сообщение persistent

rabbitTemplate.convertAndSend(
    "ex.order",
    "order.created",
    message -> {
        message.getMessageProperties()
               .setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return message;
    }
);


📌 Только так сообщения **переживают рестарт брокера**

---

# 5️⃣ Что реально даёт отказоустойчивость (итоговая таблица)

|Механизм|Защищает от|
|---|---|
|Cluster|падение ноды|
|Quorum queue|потеря сообщений|
|Durable entities|рестарт|
|Consumer ACK|падение consumer|
|Producer confirms|потеря при отправке|

---

# 🧠 Реальный production-рецепт (BEST PRACTICE)

✅ 3 ноды RabbitMQ  
✅ Quorum queues  
✅ Manual ACK  
✅ Idempotent consumer  
✅ Producer confirms  
✅ DLQ + retry

---

# 🧩 ВАЖНОЕ понимание

> RabbitMQ **НЕ гарантирует exactly-once**  
> Он гарантирует **не потерять сообщение**  
> Exactly-once достигается **логикой consumer**