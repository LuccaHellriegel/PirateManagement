pushd .
cd ..
mvn spring-boot:run &
FOO_PID=$!
sleep 7
mvn springdoc-openapi:generate && kill $FOO_PID
popd