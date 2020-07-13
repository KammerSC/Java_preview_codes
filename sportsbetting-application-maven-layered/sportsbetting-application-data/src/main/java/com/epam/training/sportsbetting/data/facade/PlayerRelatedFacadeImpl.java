package com.epam.training.sportsbetting.data.facade;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.data.crud.PlayerRepository;
import com.epam.training.sportsbetting.data.crud.UserRepository;
import com.epam.training.sportsbetting.data.crud.WagerRepository;
import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;

/** Implementation of {@link PlayerRelatedFacade}.
 * */
public class PlayerRelatedFacadeImpl implements PlayerRelatedFacade {
    private UserRepository userRepo;
    private PlayerRepository playerRepo;
    private WagerRepository wagerRepo;

    @Override
    public UserEntity findUserByEmail(String email) {
        checkNull(email);
        UserEntity user = userRepo.findByEmail(email);
        checkReturnNull(user);
        return user;
    }

    @Override
    public PlayerEntity findPlayerById(long id) {
        isValidId(id);
        PlayerEntity player = playerRepo.findById(id);
        checkReturnNull(player);
        return player;
    }

    @Override
    public PlayerEntity findPlayerByUsermail(String email) {
        checkNull(email);
        PlayerEntity player = playerRepo.findByUserEmail(email);
        checkReturnNull(player);
        return player;
    }

    @Override
    public void savePlayer(PlayerEntity player) {
        checkNull(player);
        PlayerEntity previous = playerRepo.findById(player.getId());
        if (previous == null || previous.getVersion() == player.getVersion()) {
            playerRepo.save(player);
        } else {
            throw new ConcurrentModificationException("Entity modified by an other transaction.");
        }
    }

    @Override
    public WagerEntity findWagerById(long id) {
        isValidId(id);
        WagerEntity wager = wagerRepo.findById(id);
        checkReturnNull(wager);
        return wager;
    }

    @Override
    public List<WagerEntity> findAllWagerOfPlayer(long id) {
        isValidId(id);
        return wagerRepo.findByPlayerId(id);
    }

    @Override
    public void saveWager(WagerEntity wager) {
        checkNull(wager);
        wagerRepo.save(wager);
    }

    @Override
    public void deleteWager(long id) {
        isValidId(id);
        wagerRepo.deleteById(id);
    }

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setPlayerRepo(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Autowired
    public void setWagerRepo(WagerRepository wagerRepo) {
        this.wagerRepo = wagerRepo;
    }

    /** Validator for reference type parameters.
     * @param obj 
     * @throws IllegalArgumentException if the given object is null.
     * */
    protected void checkNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Given parameter cannot be null.");
        }
    }

    /** Validator for reference type returns.
     * @param obj 
     * @throws NoSuchElementException if the given obj is null.
     * */
    protected void checkReturnNull(Object obj) {
        if (obj == null) {
            throw new NoSuchElementException("Returned object cannot be null.");
        }
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

}
