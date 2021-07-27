package com.studentwarehouse.repository;

import com.studentwarehouse.repository.entity.AddressEntity;
import com.studentwarehouse.repository.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
