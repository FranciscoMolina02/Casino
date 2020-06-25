package com.casino.api.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.casino.api.objects.Roulette;

@Repository
public interface RouletteRepository extends CrudRepository<Roulette, Long> {

}
