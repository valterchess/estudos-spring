package com.baeldung;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/***
 * A classe de inicialização, neste caso,
 * implementa a interface WebApplicationInitializer
 * Criamos um objeto context (Spring context) do tipo  AnnotationConfigWebApplicationContext()
 * que determina que o tipo de configuração que será utilizado
 * é exclusivamente baseado em anotações (Annotations)
 * E em seguida especificamos em qual|quais pacote(s)
 * as classes e componentes de configuração (beans)
 * a serem verificadas estarão.
 *
 * A criação do dispatcher determina o ponto de entrada da aplicação Web
 * A classe DispatcherServlet pode substituir completamente o arquivo web.xml
 *
 * Mas contextualizando, um arquivo web.xml
 * com as mesmas configurações do dispatcher seria assim:
 *
 * <context:component-scan base-package="com.baeldung.controller" />
 * <mvc:annotation-driven />
 */

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        var context = new AnnotationConfigWebApplicationContext();
        context.scan("com.baeldung");
        container.addListener(new ContextLoaderListener(context));

        var dispatcher = container
                .addServlet("mvc", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
