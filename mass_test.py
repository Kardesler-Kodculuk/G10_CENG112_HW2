import analyiser
import os
from typing import Dict, List

k: int = 1000
results: Dict[int, str] = {}
counts: List[Dict[str, int]] = []
for i in range(0, k + 1):
	os.system("rm output.txt")
	os.system("cd bin/ && java Main "+ str(i) +" >> ../output.txt")
	counts_per: Dict[str, int] = {"Marketing Analyst": 0, "Storage Chief": 0, "Customer": 0,  None:0}
	test_result = analyiser.Test.main("output.txt")
	if test_result is None:
		test_result = "0"
		results[i] = test_result
	else:
		results[i] = test_result[0]
		for count in test_result[1]:
			counts_per[count] = test_result[1][count]
	success = "FAIL   "
	counts.append(counts_per)
	if test_result == "0":
		success = "SUCCESS"
	print("  {:04}".format(i + 1) + "/" + str(k) + "   " + success, end="\r", flush= True)

str_: str = "Size, Queue Length, Marketing Analyst Call Count, Storage Chief Call Count, Customer Call Count \n"
l = 0
for result in results:
	str_ += str(result) + "," + results[result] + "," + str(counts[l]["Marketing Analyst"]) +"," + str(counts[l]["Storage Chief"]) + "," + str(counts[l]["Customer"]) + "\n"
	l = l + 1
with open("output.csv", "w") as file_:
	file_.write(str_)

print(set(results.values()))
