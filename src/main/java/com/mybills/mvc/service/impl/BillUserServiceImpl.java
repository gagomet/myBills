package com.mybills.mvc.service.impl;

import com.google.common.collect.Lists;
import com.mybills.mvc.domain.BillUser;
import com.mybills.mvc.service.IBillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Padonag on 28.12.2014.
 */
//@Service("billUserService")
@Service
@Repository
@Transactional
public class BillUserServiceImpl implements IBillUserService {
    @Autowired

    private JpaRepository<BillUser, String> usersRepozitory;

    @Override
    @Transactional(readOnly = true)
    public List<BillUser> getAllUsers() {
        return Lists.newArrayList(usersRepozitory.findAll());
    }

    @Override
    public BillUser getUserByLogin(String login) {
        return usersRepozitory.findOne(login);
    }

    @Override
    public BillUser saveUser(BillUser user) {
        return usersRepozitory.save(user);
    }


    @Override
    public Page<BillUser> getUsersByPages(Pageable pageable) {
        return usersRepozitory.findAll(pageable);
    }

}
