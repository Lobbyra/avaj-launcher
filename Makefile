all	:
	find * -name "*.java" > sources.txt
	javac -d bin @sources.txt

run : all
	java -classpath bin App scenario.txt

clean:
	rm -rf bin
	rm -f sources.txt 2> /dev/null

re: clean all
