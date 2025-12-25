# 🧠 Что такое **idempotent** (простыми словами)

> **Idempotent операция** — это операция,  
> которую можно выполнить **сколько угодно раз**,  
> и результат **не изменится** после первого успешного выполнения.

---

## 🔹 Пример НЕ idempotent ❌

`balance = balance + 100;`

Если выполнить 2 раза → деньги спишутся 2 раза ❌

---

## 🔹 Пример idempotent ✅

if (!paymentExists(paymentId)) {
    savePayment(paymentId, 100);
}


Повторный вызов → **ничего не сломается**

---

# 🧩 Почему idempotent ОБЯЗАТЕЛЕН в RabbitMQ

RabbitMQ = **at-least-once delivery**

Это значит:

- сообщение **может прийти повторно**
    
- consumer **обязан быть готов**
    

📌 **Именно consumer отвечает за exactly-once эффект**

---

# 🏗 Архитектура примера

Producer
   ↓
Exchange
   ↓
Queue
   ↓
Idempotent Consumer
   ↓
Database (event_id UNIQUE)


---

# 📨 Формат сообщения (ОБЯЗАТЕЛЬНО!)

{
  "eventId": "b5f7c9c2-8c42-4a14-8c31-123456789abc",
  "orderId": 42,
  "amount": 100
}

📌 `eventId` — **ключ идемпотентности**

---

# 🧪 Реализация: Idempotent Consumer (Spring + RabbitMQ)

---

## 1️⃣ Entity для уже обработанных событий

@Entity
@Table(
		name = "processed_event",
     uniqueConstraints = @UniqueConstraint(columnNames = "eventId"))
public class ProcessedEvent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String eventId;

    protected ProcessedEvent() {}

    public ProcessedEvent(String eventId) {
        this.eventId = eventId;
    }
}


---

## 2️⃣ Repository

public interface ProcessedEventRepository
        extends JpaRepository<ProcessedEvent, Long> {

    boolean existsByEventId(String eventId);
}


---

## 3️⃣ Consumer (КЛЮЧЕВОЙ КОД)

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final ProcessedEventRepository repository;

    @RabbitListener(
        queues = "q.order",
        ackMode = "MANUAL"
    )
    @Transactional
    public void handle(
        OrderEvent event,
        Channel channel,
        @Header(AmqpHeaders.DELIVERY_TAG) long tag
    ) throws Exception {

        // 🔹 1. Проверка идемпотентности
        if (repository.existsByEventId(event.getEventId())) {
            log.warn("Duplicate event {}", event.getEventId());
            channel.basicAck(tag, false);
            return;
        }

        // 🔹 2. Бизнес-логика
        processOrder(event);

        // 🔹 3. Фиксируем обработку
        repository.save(new ProcessedEvent(event.getEventId()));

        // 🔹 4. Ack ТОЛЬКО ПОСЛЕ коммита
        channel.basicAck(tag, false);
    }

    private void processOrder(OrderEvent event) {
        log.info("Processing order {}", event.getOrderId());
    }
}


---

# ⚠️ Почему именно ТАК, а не иначе

|Ошибка|Почему плохо|
|---|---|
|Ack ДО бизнес-логики|потеря сообщения|
|Нет eventId|нельзя защититься от дублей|
|Auto ack|нет контроля|
|Нет @Transactional|возможна рассинхронизация|

---

# 🧠 Что будет при падениях

|Сценарий|Результат|
|---|---|
|Consumer упал ДО save|сообщение вернётся|
|Consumer упал ПОСЛЕ save|дубликат → игнор|
|Rabbit перешлёт сообщение|безопасно|

---

# 🔐 Альтернатива: idempotency через UNIQUE constraint

`CREATE UNIQUE INDEX ux_event_id ON processed_event(event_id);`

try {
    repository.save(new ProcessedEvent(eventId));
} catch (DataIntegrityViolationException e) {
    // дубликат → safe ignore
}


📌 **Это самый надёжный способ**

---

# 🧠 Золотое правило (запомни)

> **RabbitMQ = at-least-once**  
> **Consumer = idempotent**  
> **Exactly-once = эффект, а не гарантия**