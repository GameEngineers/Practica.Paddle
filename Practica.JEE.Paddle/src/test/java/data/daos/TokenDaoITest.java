package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        Token token = (Token) daosService.getMap().get("tu1");
        User user = (User) daosService.getMap().get("u4");
        assertEquals(token, tokenDao.findByUser(token.getUser()));
        assertNull(tokenDao.findByUser(user));
    }
    
    @Test
    public void testDeleteAllExpiredTokensByUser(){
    	User user5 = (User)daosService.getMap().get("u5");
    	User user1 = (User)daosService.getMap().get("u1");
    	tokenDao.deleteAllExpiredTokensByUser(user5);
    	assertNull(tokenDao.findByUser(user5));
    	assertNotNull(tokenDao.findByUser(user1));
    }
    
    
    @Test
    public void testDeleteAllExpiredTokens(){
    	tokenDao.deleteAllExpiredTokens();
    	assertNull(tokenDao.findByUser((User)daosService.getMap().get("u5")));
    	assertNull(tokenDao.findByUser((User)daosService.getMap().get("u6")));
    	assertNull(tokenDao.findByUser((User)daosService.getMap().get("u7")));
    	assertNotNull(tokenDao.findByUser((User)daosService.getMap().get("u0")));
    	assertNotNull(tokenDao.findByUser((User)daosService.getMap().get("u1")));
    	assertNotNull(tokenDao.findByUser((User)daosService.getMap().get("u2")));
    	assertNotNull(tokenDao.findByUser((User)daosService.getMap().get("u3")));
    	
    }

}
