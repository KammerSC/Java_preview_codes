package com.epam.training.sportsbetting.data.facade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.data.crud.BetRepository;
import com.epam.training.sportsbetting.data.crud.OutcomeOddRepository;
import com.epam.training.sportsbetting.data.crud.OutcomeRepository;
import com.epam.training.sportsbetting.data.crud.SportEventEntityRepository;
import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;

/** Facade class that implements {@link EventRelatedFacade}.
 * */
public final class EventRelatedFacadeImplementation implements EventRelatedFacade {
    private SportEventEntityRepository eventRepo;
    private BetRepository betRepo;
    private OutcomeRepository outcomeRepo;
    private OutcomeOddRepository oddRepo;

    @Override
    public SportEventEntity findSportEventById(long id) {
        isValidId(id);
        SportEventEntity event = eventRepo.findById(id);
        isNotNull(event);
        return event;
    }

    @Override
    public List<SportEventEntity> findAllSportEventThatEndsAfter(LocalDateTime time) {
        if (time != null) {
            return eventRepo.findAllWithEndAfter(time);
        } else {
            throw new IllegalArgumentException("Parameter time cannot be null");
        }
    }

    @Override
    public BetEntity findBetById(long id) {
        isValidId(id);
        BetEntity bet = betRepo.findById(id);
        isNotNull(bet);
        return bet;
    }

    @Override
    public List<BetEntity> findBetsByEventId(long id) {
        isValidId(id);
        return betRepo.findByEventId(id);
    }

    @Override
    public OutcomeEntity findOutcomeById(long id) {
        isValidId(id);
        OutcomeEntity outcome = outcomeRepo.findById(id);
        isNotNull(outcome);
        return outcome;
    }

    @Override
    public List<OutcomeEntity> findOutcomesByBetId(long id) {
        isValidId(id);
        return outcomeRepo.findByBetId(id);
    }

    @Override
    public OutcomeOddEntity findOutcomeOddById(long id) {
        isValidId(id);
        OutcomeOddEntity odd = oddRepo.findById(id);
        isNotNull(odd);
        return odd;
    }

    @Override
    public OutcomeOddEntity findValidOddOfOutcome(long id, LocalDateTime time) {
        isValidId(id);
        OutcomeOddEntity odd = oddRepo.findValidOddOfOutcome(id, time);
        isNotNull(odd);
        return odd;
    }

    @Autowired
    public void setEventRepo(SportEventEntityRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Autowired
    public void setBetRepo(BetRepository betRepo) {
        this.betRepo = betRepo;
    }

    @Autowired
    public void setOutcomeRepo(OutcomeRepository outcomeRepo) {
        this.outcomeRepo = outcomeRepo;
    }

    @Autowired
    public void setOddRepo(OutcomeOddRepository oddRepo) {
        this.oddRepo = oddRepo;
    }

    /** Id validator.
     * @param id is a number.
     * @return true if the given id is more than 0.
     * @throws IllegalArgumentException if the given id is 0 or less.
     * */
    protected boolean isValidId(long id) {
        if (id > 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Id must be more than 0.");
        }
    }

    /** Validates the given entity if its not null.
     * @param obj entity.
     * @throws NoSuchElementException if the given entity is null.
     * */
    protected void isNotNull(Object obj) {
        if (obj == null) {
            throw new NoSuchElementException("There is no Entity with the given ID.");
        }
    }

}
