package com.frisk.enterpriselaboration.Repositorys;

import com.frisk.enterpriselaboration.Enteties.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}