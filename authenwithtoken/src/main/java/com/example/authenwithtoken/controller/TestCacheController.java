package com.example.authenwithtoken.controller;

import com.example.authenwithtoken.service.TabUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j

@RestController
@RequiredArgsConstructor
public class TestCacheController {

    private final TabUserService tabUserService;
    private final CacheManager cacheManager;

    @GetMapping("/test-cache/get-user")
    public TabUserService.TabUserServiceDto get(
            @Param("userId") String userId
    ) {
        log.info("userId : {} : In user controller : get-user ", userId);
        return tabUserService.getById(userId);
    }


    @PostMapping("/test-cache/update-user")
    public TabUserService.TabUserServiceDto update(
            @RequestBody TabUserService.TabUserServiceDto request
            ) {
        log.info("userId : {} : In user controller : update-user ", request.getUserId());
        return tabUserService.updateTabUser(request);
    }

    @PostMapping("/test-cache/delete-user")
    public void delete(
            @Param("userId") String userId
    ) {
        log.info("userId : {} : In user controller : delete-user ", userId);
        tabUserService.deleteUser(userId);
    }






    @GetMapping("/test-cache/put-manual")
    public void putManual(
            @Param("key") String key,
            @Param("value") String value
    ) {
        log.info("in /test-cache/put-manual key : {} , value : {}", key, value);
        cacheManager.getCache("test-manual").put(key, value);
    }


    @GetMapping("/test-cache/get-manual")
    public String getManual(
            @Param("key") String key
    ) {
        log.info("in /test-cache/get-manual key : {} ", key);
        return cacheManager.getCache("test-manual").get(key, String.class);
    }



    @GetMapping("/test-cache/evict-manual")
    public void evictCache(
            @Param("key") String key
    ) {
        log.info("in /test-cache/evict-manual key : {} ", key);
        cacheManager.getCache("test-manual").evict(key);
    }



}
