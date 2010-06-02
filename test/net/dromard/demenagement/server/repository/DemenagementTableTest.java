package net.dromard.demenagement.server.repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import net.dromard.demenagement.shared.model.Demenagement;

public class DemenagementTableTest extends TestCase {
    public void test() {
        try {

            DemenagementTable table = new DemenagementTable();
            List<Demenagement> list = table.get();
            list.add(new Demenagement() {
                {
                    setId(0);
                    setDate(new Date());
                }
            });
            list.add(new Demenagement() {
                {
                    setId(1);
                    setDate(new Date());
                }
            });
            table.save(list);

            list = table.get();
            assertTrue(list.size() > 0);
            for (Demenagement demenagement : list) {
                assertTrue(demenagement.getId() >= 0);
                assertNotNull(demenagement.getDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
