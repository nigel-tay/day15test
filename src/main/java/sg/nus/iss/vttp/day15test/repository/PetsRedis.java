package sg.nus.iss.vttp.day15test.repository;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.nus.iss.vttp.day15test.model.Pet;

@Repository
public class PetsRedis {
    
    @Autowired
    private RedisTemplate<String, Object> template;

    public void addPet(Pet pet) {
        template.opsForHash().put("PET_HASH", pet.getName(), pet);
    }

    public Map<Object, Object> retrieveAllValues(String redisKey) {
        return template.opsForHash().entries(redisKey);
    }
}
