class Stack:
    def __init__(self):
        self.stack_list = []

    def push(self, new_element):
        self.stack_list.append(new_element)
    def pop(self):
        return self.stack_list.pop()

    def peek(self):
        return self.stack_list[-1]

    def is_empty(self):
        return self.stack_list == []

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
        self.__head = 0
        self.__queue = []

    def enqueue(self, new_element):
        self.__queue.append(new_element)

    def dequeue(self):
        object_ = self.__queue[self.__head]
        self.__head += 1
        return object_

    def next_item(self):
        return self.__queue[self.__head]

    def is_empty(self):
        return len(self.__queue) == self.__head

    def to_string(self):
        print(self.__queue)
        print(self.__head)

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
            '''
            if component != storage_addition.next_item():
                print(line)
                print(component)
                print(storage_addition.next_item())
                storage_addition.to_string()
                fail = True
                print("FAILURE IN STORAGE CHIEF: Mismatch")
                break
                pass
            else:
            '''
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
