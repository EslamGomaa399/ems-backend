package com.egomaa.ems.emsbackend.repository;


import com.egomaa.ems.emsbackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
