package com.vlad.learn.notebook.web;

import com.vlad.learn.notebook.NotebookApplication;
import com.vlad.learn.notebook.core.entity.UserAccount;
import com.vlad.learn.notebook.core.repository.UserAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;


@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotebookApplication.class)
public class IntegrationTest {


    @Autowired
    private UserAccountRepository userAccountRepository;

    private UserAccount userAccount;
    private UserAccount userAccountGraph;

    @PostConstruct
    public void init() {
        userAccount = userAccountRepository.findByEmail("mail@mail.com").get();
        userAccountGraph = userAccountRepository.getByEmail("mail@mail.com").get();
    }

    @Test
    public void contextStarts() {
    int i = 2;
    }

}
