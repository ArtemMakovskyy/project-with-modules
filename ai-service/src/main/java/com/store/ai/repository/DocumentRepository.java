package com.store.ai.repository;

import com.store.ai.model.LoadedDocument;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<LoadedDocument, UUID> {

    boolean existsByFilenameAndContentHash(String filename, String contentHash);

}