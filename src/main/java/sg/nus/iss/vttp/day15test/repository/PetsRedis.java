package sg.nus.iss.vttp.day15test.repository;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.nus.iss.vttp.day15test.model.Pet;

@Repository
public class PetsRedis {
    private String redisKey = "PET_HASH";
    
    @Autowired
    private RedisTemplate<String, Object> template;

    public void addPet(Pet pet) {
        String hashKey = pet.getId();
        template.opsForHash().put(redisKey, hashKey, pet);
    }

    public Object getPet(String id) {
        Object retrievedPet = template.opsForHash().get(redisKey, id);
        System.out.println(retrievedPet.toString());
        return retrievedPet;
    }

    public Map<Object, Object> retrieveAllValues(String redisKey) {
        return template.opsForHash().entries(redisKey);
    }
}
