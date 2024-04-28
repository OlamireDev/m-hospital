package com.olamiredev.mhospital.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class FileCleanUpService {

    private final ConcurrentHashMap<LocalDateTime, FileSystemResource> generatedFilesMap = new ConcurrentHashMap<>();

    public void updateGeneratedFile(FileSystemResource systemResource){
        generatedFilesMap.put(LocalDateTime.now().plusMinutes(5), systemResource);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void removeExpiredResources() {
        int counter =0;
        for(var entry: generatedFilesMap.entrySet()){
            if(LocalDateTime.now().isAfter(entry.getKey())) {
                entry.getValue().getFile().delete();
                generatedFilesMap.remove(entry.getKey());
                counter++;
            }
        }
        log.info("Successfully cleaned up {} files", counter);
    }

}
