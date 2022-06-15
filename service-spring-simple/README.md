Simplified version of the app. Just one controller, no explicit architecture, basic error handling,
naive domain objects.

Examples with https://httpie.io/cli

# getAll:

http GET localhost:8080/api/v1/treasures
http GET localhost:8080/api/v1/crews

# get in radius:

http GET localhost:8080/api/v1/treasures/radius?"y=1&&x=1&&radius=10"

The "" are necessary because of the encoding of the floats.
Might need to change this once we have FE.

# assign crews

http PATCH localhost:8080/api/v1/treasures/{TREASURE_ID}/assignedCrews?crew={CREW_ID}

This will return an error if the crew has not enough capacity.

Replace the {} with real ids (see the log output, demo data has been added to the embedded mongo).

# get views:

http GET localhost:8080/api/v1/view/treasures/{TREASURE_ID}
http GET localhost:8080/api/v1/view/crews/{CREW_ID}

Replace the {} with real ids (see the log output, demo data has been added to the embedded mongo).