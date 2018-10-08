#!/bin/bash
VERSION=$(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2)

pack200 --no-gzip $(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2).pack ./launcher/build/libs/launcher-*-all.jar
scp -P 2202 $(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2).pack root@worldautomation.net:/storage/WA-Web-Pack/public/launcher/versions/.

# Updated path to current
ssh -p 2202 root@worldautomation.net "rm /storage/WA-Web-Pack/public/launcher/latest.json;sed 's/UPDATE/$VERSION/g' /storage/WA-Web-Pack/public/launcher/template.json >> /storage/WA-Web-Pack/public/launcher/latest.json"

# This would update the actual modpack, but not needed here... we use this as a PHP script on the WA-Mod-Pack repo now.
#ssh -p 2202 root@worldautomation.net "/storage/launcher/creator.sh"
rm $(echo ./launcher/build/libs/launcher-*-all.jar | cut -d- -f2).pack
