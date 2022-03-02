package com.baeldung;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/***
 * @Configuration É o principal artefacto usado
 * pela configuração do Spring baseada em java;
 * Sendo meta-anotado propriamente com @Component,
 * transformando as classes anotadas em beans padrão
 * tornando-as candidatas à verificação de componentes.
 *
 * As classes @Configuration são as fontes de definição de bean
 * para o IoC (Injection of Control) Container do Spring core.
 *
 * Ao configurar utilizando o spring-webmvc, além da @Configuration
 * precisamos definir explicitamente @EnableWebMvc para as configurações
 * padrão do Spring MVC e @ComponentScan para especificar
 * pacotes para verificar componentes.
 *
 * @EnableWebMvc Fornece a configuração do Spring Web MVC,
 * como configurar o servlet do dispatcher,
 * habilitar as anotações @Controller e @RequestMapping e configurar outros padrões.
 *
 * @ComponentScan configura a diretiva de verificação de componentes,
 * especificando os pacotes a serem verificados.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.baeldung.controller")
public class WebConfig {
}
