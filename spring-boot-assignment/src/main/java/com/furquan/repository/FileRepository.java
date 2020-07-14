package com.furquan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.furquan.entity.FileEntity;

/**
 * @author furquan
 *
 */
@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
