#!/bin/bash
# Leider gibt es keine Konfigurationsfiles für die Postgres DB

fly postgres create --autostart --name=businesstrips-db --region=ams --org=personal
fly postgres attach businesstrips-db  --app=businesstrips-backend