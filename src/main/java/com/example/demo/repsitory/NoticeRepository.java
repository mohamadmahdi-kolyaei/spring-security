package com.example.demo.repsitory;

import com.example.demo.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query(value = "select n from Notice n where CURDATE() BETWEEN n.noticeBegDt AND n.noticeEndDt")
    List<Notice> findAllActiveNotices();

}
