package com.mybills.mvc.service;

import com.mybills.mvc.domain.BillUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Padonag on 28.12.2014.
 */
public interface IBillUserService {
    public List<BillUser> getAllUsers();
    public BillUser getUserByLogin(String login);
    public BillUser saveUser(BillUser user);
    public Page<BillUser> getUsersByPages(Pageable pageable);
}
