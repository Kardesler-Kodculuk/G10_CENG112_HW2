def determine_component(string):
    components = ["RAM", "CPU", "Graphics Card", "Motherboard", "Cache"]
    for component in components:
        if component in string:
            return component

def determine_factory(string):
    factory = ["Storage chief", "Marketing analyst", "Customer"]
    for factory_part in factory:
        if factory_part in string:
            return factory_part

def determine_result(string):
    results = ["FAIL", "SUCCESS"]
    for result in results:
        if result in string:
            return result

class Queue:
    def __init__(self):
        self.head = 0
        self.queue = []

    def enqueue(self, new_element):
        self.queue.append(new_element)

    def dequeue(self):
        object_ = self.queue[self.head]
        self.head += 1
        return object_

    def next_item(self):
        return self.queue[self.head]

    def is_empty(self):
        return len(self.queue) == self.head

    def to_string(self):
        for i in range(0, len(self.queue)):
            if (i == self.head):
                print("*", i, self.queue[i])
                continue
            print(i, self.queue[i])

storage_addition = Queue()
counts = {
    "RAM": 0,
    "CPU": 0,
    "Graphics Card": 0,
    "Motherboard": 0,
    "Cache": 0
}

fail = False
file = open("output.txt", "r")
data = file.read().split("\n")
file.close()
for line in data:
    if line == "":
        break
    factory_component = determine_factory(line)
    component = determine_component(line)
    result = determine_result(line)
    if factory_component == "Marketing analyst":
        storage_addition.enqueue(component)
    elif factory_component == "Storage chief":
        if storage_addition.is_empty():
            if result != "FAIL":
                fail = True
                print("FAILURE IN STORAGE CHIEF: Nonexistent element")
                break
        else:
            if component != storage_addition.next_item():
                print(line)
                print(component)
                print(storage_addition.next_item())
                storage_addition.to_string()
                fail = True
                print("FAILURE IN STORAGE CHIEF: Mismatch")
                break
            else:
                comp = storage_addition.dequeue()
                counts[comp] += 1
                if result != "SUCCESS":
                    print("FAILURE IN STORAGE CHIEF: Wrong reaction")
                    break
    elif factory_component == "Customer":
        if counts[component] >= 1:
            if result != "SUCCESS":
                fail = True
                print(line)
                print(component)
                print("FAILURE IN CUSTOMER: Wrong reaction - Success")
                break
            else:
                counts[component] -= 1
        else:
            if result != "FAIL":
                fail = True
                print(line)
                print("FAILURE IN CUSTOMER: Wrong reaction - Fail")
                break

if fail:
    print("ERRORS DETECTED IN SYSTEM")
else:
    print("SUCCESSFULL INTEGRATION TESTING")
