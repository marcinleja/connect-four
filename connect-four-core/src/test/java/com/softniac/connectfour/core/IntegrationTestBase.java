package com.softniac.connectfour.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@TestPropertySource({ "classpath:connect-four.properties" })
@ContextConfiguration(classes = { ConnectFourCoreConfig.class })
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationTestBase {

}
