#!/usr/bin/python

'''
Created on Mar 24, 2012

@author: ganzhi
'''

from pymongo import Connection

conn = Connection()
db = conn.test

coll = db.atomicNum
sum = 0
for i in range(100000):
    while(True):
        atomic_num = coll.find_one()
        version = atomic_num["version"]
        num = atomic_num["num"]
        coll.update({"name":"jjj", "version":version},{"$inc":{"num":1, "version":1}})
        status = db.last_status()
        if status["updatedExisting"]:
            break
        else:
            sum = sum+1
            print "RETRY: " + str(sum)

print coll.find_one()


