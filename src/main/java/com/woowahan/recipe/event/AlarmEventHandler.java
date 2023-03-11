package com.woowahan.recipe.event;

import com.woowahan.recipe.domain.entity.AlarmEntity;
import com.woowahan.recipe.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Async
@Slf4j
@Transactional(readOnly = true)
public class AlarmEventHandler {

    private final AlarmRepository alarmRepository;

    @EventListener
    public void createAlarm(AlarmEvent e) {
        AlarmEntity alarm = alarmRepository.save(e.getAlarm());
        log.info(alarm.getAlarmType() + "is created");
        throw new RuntimeException();
    }
}
