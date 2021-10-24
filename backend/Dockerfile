FROM java:8

COPY *.jar /app.jar 

CMD ["--server.port=8082"]

EXPOSE 8082

ENTRYPOINT ["java","-jar","/app.jar"]