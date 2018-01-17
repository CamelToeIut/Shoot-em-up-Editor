all : doc
	javac *.java
	javac geometrie/*.java
	javac audio/*.java

doc : 
	javadoc -charset utf8 -d doc/ *.java geometrie/*.java audio/*.java

clean :
	rm -rf doc *.class *~ geometrie/*.class geometrie/*~ audio/*~ audio/*.class audio/*/*~ audio/*/*.class audio/*/*/*~ audio/*/*/*.class
