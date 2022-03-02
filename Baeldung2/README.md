## web.xml vs Initializer with Spring

<li style="list-style: none;">
    Este exemplo é baseado no artigo da baeldung:
    <a href="https://www.baeldung.com/spring-xml-vs-java-config"> 
        web.xml vs Initializer com Spring
    </a>
</li>

Ele ilustra três diferentes formas de configuração de um DispatcherServlet
que se encontra nas versões mais recentes da plataforma Spring.

As abordagens em questão são:

<li>
    Uma configuração XML e um arquivo web.xml
</li>
<br>
<li>
    Em seguida, migraremos a declaração Servlet do arquivo web.xml
    para a configuração Java, mas deixaremos qualquer outra configuração em XML
</li>
<br>
<li>
    Por fim, na terceira e última etapa da refatoração,
    teremos um projeto 100% configurado em Java
</li>

Sendo um dos principais conceitos do Spring MVC,
o DispatcherServlet é um distribuidor central 
para manipuladores/controladores de solicitações HTTP, 
por exemplo, para controladores de interface do usuário da Web
ou exportadores de serviços remotos baseados em HTTP.
Distribui(despacha) para manipuladores registrados 
para processar uma solicitação Web, fornecendo recursos
convenientes de mapeamento e tratamento de exceptions.
(Definição da <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/DispatcherServlet.html">
                                                                                                                                    documentação do Spring. </a>)