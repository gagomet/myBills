package com.mybills.mvc.repository;

import com.mybills.mvc.domain.BillUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Padonag on 28.12.2014.
 */
public interface IUsersRepozitory extends JpaRepository<BillUser, String> {
}
