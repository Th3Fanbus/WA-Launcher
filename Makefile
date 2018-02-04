JJ=java -jar
LAUNCHER=launcher-6.1.3-all.jar
LAUNCHER_MAIN=launcher-main-6.1.3-all.jar

all: Launcher

Launcher:
	./gradlew clean build

run:
	$(JJ) ./launcher/build/libs/$(LAUNCHER)

run-main:
	$(JJ) ./launcher-main/build/libs/$(LAUNCHER_MAIN)
