package com.example.demo.controller;

import com.example.demo.model.Notice;
import com.example.demo.repsitory.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;


@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository noticeRepository;



    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(Duration.ofSeconds(60)))
                    .body(notices);
        } else {
            return null;
        }
    }
}
