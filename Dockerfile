FROM daocloud.io/mouge/tomcat-maven-node

COPY . /NoProblem

WORKDIR /NoProblem/src/main/webapp/lite-vue
RUN npm install
RUN npm run build

WORKDIR /NoProblem

RUN mv /NoProblem/src/main/resources/product-app.properties  /NoProblem/src/main/resources/app.properties
RUN mvn install
RUN rm -rf $CATALINA_HOME/webapps/ROOT && rm -rf /NoProblem/src/main/webapp/vue-ljun && mv target/NoProblem $CATALINA_HOME/webapps/ROOT