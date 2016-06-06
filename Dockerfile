FROM daocloud.io/mouge/tomcat-maven-node

COPY . /JSchoolsDebug

WORKDIR /JSchoolsDebug/src/main/webapp/vue-ljun
RUN npm install
RUN npm run build

WORKDIR /JSchoolsDebug

RUN mv /JSchoolsDebug/src/main/resources/product-app.properties  /JSchoolsDebug/src/main/resources/app.properties
RUN mvn install
RUN rm -rf $CATALINA_HOME/webapps/ROOT && rm -rf /JSchoolsDebug/src/main/webapp/vue-ljun && mv target/JSchoolsDebug $CATALINA_HOME/webapps/ROOT