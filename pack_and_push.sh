#!/bin/sh
pack200 --no-gzip $(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2).pack ./launcher/build/libs/launcher-*-all.jar
scp -P 2202 $(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2).pack root@worldautomation.net:/storage/launcher/versions/.
#ssh -p 2202 root@worldautomation.net "/storage/launcher/creator.sh"
