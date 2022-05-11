package com.project.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.myapp.models.Batch;

public interface BatchRepository  extends JpaRepository<Batch,Long>{
  public Batch findByBatchName(String batchName);
  public Boolean existsByBatchName(String batchName);
}
