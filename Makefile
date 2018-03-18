JJ=java -jar

all: Launcher

Launcher:
	./gradlew build

clean:
	./gradlew clean

run:
	$(JJ) ./launcher/build/libs/launcher-*-all.jar

pack: Launcher
	./pack_and_push.sh
