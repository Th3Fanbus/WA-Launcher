JJ=java -jar
LAUNCHER=launcher-*-all.jar
LAUNCHER_MAIN=launcher-main-*-all.jar

all: Launcher

Launcher:
	./gradlew clean build

run:
	$(JJ) ./launcher/build/libs/$(LAUNCHER)

run-main:
	$(JJ) ./launcher-main/build/libs/$(LAUNCHER_MAIN)
