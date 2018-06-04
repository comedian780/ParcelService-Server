import ast
import requests
import subprocess

vm_url = "192.168.56.101"

# Post request to the API
r = requests.post("http://" + str(vm_url) + ":8443/parcel/size", json={"length": 1, "width": 2, "height": 3, "size": ""})


# Parse response data
size = r.json()['size']

if(str(size) == "XS"):
  exit(0)
else:
exit(1)
