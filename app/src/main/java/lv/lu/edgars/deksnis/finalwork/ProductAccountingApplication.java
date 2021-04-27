package lv.lu.edgars.deksnis.finalwork;

import lv.lu.edgars.deksnis.finalwork.config.AppConfiguration;
import lv.lu.edgars.deksnis.finalwork.ui.ConsulUi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductAccountingApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        context.getBean(ConsulUi.class).run();
    }

}
