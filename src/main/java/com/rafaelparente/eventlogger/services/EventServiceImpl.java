package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.dto.EventDTO;
import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import com.rafaelparente.eventlogger.models.Log;
import com.rafaelparente.eventlogger.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventDTO> findMultiple(Pageable pageable,
                                       Optional<List<EventLevel>> level,
                                       Optional<String> description,
                                       Optional<List<String>> source,
                                       Optional<List<Integer>> year,
                                       Optional<List<Integer>> month,
                                       Optional<List<Integer>> day,
                                       Optional<List<Integer>> quantity) {
        return this.eventRepository.findMultiple(pageable, level, description, source, year, month, day, quantity).getContent();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Event save(Event event) {
        if (event.getDate() == null) event.setDate(LocalDate.now());
        if (event.getQuantity() == null) event.setQuantity(1);

        return this.eventRepository.save(event);
    }

    @Override
    public Optional<Event> change(Event event, Long id) {
        return this.eventRepository.findById(id)
                .map(repoEvent -> {
                    repoEvent.setLevel(event.getLevel());
                    repoEvent.setDescription(event.getDescription());

                    Log repoEventLog = repoEvent.getLog();
                    Log eventLog = event.getLog();
                    repoEventLog.setText(eventLog.getText());

                    repoEvent.setSource(event.getSource());
                    repoEvent.setDate(event.getDate());
                    repoEvent.setQuantity(event.getQuantity());

                    return this.eventRepository.save(repoEvent);
                });
    }

    @Override
    public Optional<Event> update(Event event, Long id) {
        return this.eventRepository.findById(id)
                .map(repoEvent -> {
                    Optional<EventLevel> level = Optional.ofNullable(event.getLevel());
                    Optional<String> description = Optional.ofNullable(event.getDescription());
                    Optional<Log> log = Optional.ofNullable(event.getLog());
                    Optional<String> source = Optional.ofNullable(event.getSource());
                    Optional<LocalDate> date = Optional.ofNullable(event.getDate());
                    Optional<Integer> quantity = Optional.ofNullable(event.getQuantity());

                    level.ifPresent(repoEvent::setLevel);
                    description.ifPresent(repoEvent::setDescription);

                    log.ifPresent(l -> {
                        Optional<String> text = Optional.ofNullable(l.getText());

                        Log repoEventLog = repoEvent.getLog();
                        text.ifPresent(repoEventLog::setText);
                    });

                    source.ifPresent(repoEvent::setSource);
                    date.ifPresent(repoEvent::setDate);
                    quantity.ifPresent(repoEvent::setQuantity);

                    return this.eventRepository.save(repoEvent);
                });
    }

    @Override
    public void delete(Long id) {
        this.eventRepository.deleteById(id);
    }

}
