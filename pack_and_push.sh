#!/bin/sh
pack200 --no-gzip $(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2).pack ./launcher/build/libs/launcher-*-all.jar
scp -P 2288 $(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2).pack root@worldautomation.net:/storage/launcher/_upload/versions/.
