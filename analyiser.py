# Compatible with Python 3.6+ due to Type hints.
# You need to redirect your console output to a file.
# This should work with the output type specified at homework specification
from typing import Dict, Tuple
import re


class Queue:
    """
    Queue class is a basic implementation of the Data
        structure Queue, which works according to FIFO
        principle
    """

    def __init__(self):
        """
        Initialise the Queue object.
        """
        self.head: int = 0
        self.queue: list = []

    def __len__(self) -> int:
        """
        Number of elements inside the Queue
        :return: the number of elements
        """
        return len(self.queue)

    def is_empty(self) -> bool:
        """
        Check if the Queue is empty
        :return: True if empty, False if not.
        """
        return len(self.queue) == self.head

    def enqueue(self, new_element: object) -> None:
        """
        Add an element to the Queue object from the back.
        :param new_element: Any element of object type or its inheritors.
        :return: None.
        """
        self.queue.append(new_element)

    def dequeue(self) -> object:
        """
        Dequeue an object if available.
        :return: the object dequeued, or None if already empty.
        """
        if not self.is_empty():
            object_ = self.queue[self.head]
            self.head += 1
            return object_
        else:
            return None

    def count(self, item: object) -> int:
        working_list = self.queue[self.head:]
        return working_list.count(item)

    @property
    def next_item(self):
        """
        Next item in the list.
        :return: Item or None.
        """
        return self.queue[self.head]

    def __repr__(self) -> str:
        """
        Return the string representation of the Queue object.
        :return: List of elements in Queue, with a star near the front.
        """
        str_: str = ""
        for i in range(0, len(self.queue)):
            if i == self.head:
                str_ += " *" + str(i) + "-" + str(self.queue[i]) + ","
                continue
            str_ += (" " + str(i) + "-" + str(self.queue[i]) + ",")
        return str_


class Test:
    """
    Main class that holds the methods to check and parse the log file.
    """

    @staticmethod
    def determine_component(string):
        """
        Determine which computer part
        (an inheritor of interface, IProduct)
        was mentioned in the string.
        :param string: String to be analysed.
        :return: Name of the IProduct member used, or None.
        """
        components = ["RAM", "CPU", "Graphics Card", "Motherboard", "Cache"]
        for component in components:
            if component in string:
                return component

    @staticmethod
    def determine_factory(string):
        """
        Determine which simulator part was
        mentioned in the string.
        :param string: String to be analysed.
        :return: Name of the simulator part used, or None.
        """
        factory = ["Storage Chief", "Marketing Analyst", "Customer"]
        for factory_part in factory:
            if factory_part in string:
                return factory_part

    @staticmethod
    def determine_result(string):
        """
        Determine whether or not the line says SUCCESS or FAIL
        :param string:
        :return: FAIL or SUCCESS, or maybe None, but that would signal
        a serious error in the log file.
        """
        results = ["FAIL", "SUCCESS"]
        for result in results:
            if result in string:
                return result

    @staticmethod
    def main(file_name: str) -> Tuple[str, Dict[str, int]]:
        """
        Main method
        :param file_name: Name of the file to be analysed.
        :return: Line number if error occurs as string.
        """
        storage_addition = Queue()
        counts: Dict[str, int] = {
            "RAM": 0,
            "CPU": 0,
            "Graphics Card": 0,
            "Motherboard": 0,
            "Cache": 0
        }

        component_sold_counts: Dict[str, int] = {
            "RAM": 0,
            "CPU": 0,
            "Graphics Card": 0,
            "Motherboard": 0,
            "Cache": 0
        }
        # File operations
        fail = False
        file = open(file_name, "r")
        data = file.read().split("REPORT:\n")
        report = data[1].split("\n")
        data = data[0].split("\n")
        file.close()
        component_mentions: Dict[str, int] = {"Marketing Analyst": 0, "Customer": 0, "Storage Chief": 0}
        for line in data:  # Loop through the lines.
            if line == "" :  # Should the line be empty, means the last line if configured properly.
                break

            # Parse the line to determine which component did what with what part
            factory_component = Test.determine_factory(line)
            if factory_component is None:
                continue
            component = Test.determine_component(line)
            result = Test.determine_result(line)
            component_mentions[factory_component] += 1
            if factory_component == "Marketing Analyst":
                storage_addition.enqueue(component)
            elif factory_component == "Storage Chief":
                if storage_addition.is_empty():
                    if result != "FAIL":
                        fail = True
                        if __name__ != "__main__":
                            return str(len(storage_addition)), component_mentions
                        print("FAILURE IN STORAGE CHIEF: Nonexistent element")
                        break
                else:
                    if component != storage_addition.next_item:

                        print("Error at the line:", line)
                        print("Returned:", component)
                        print("Expected:", storage_addition.next_item)
                        print("Queue output:", storage_addition)
                        fail = True
                        print("FAILURE IN STORAGE CHIEF: Mismatch")
                        break
                    else:
                        if result != "SUCCESS":
                            print("Error at the line: ", line)
                            print("FAILURE IN STORAGE CHIEF: Wrong reaction, expected SUCCESS got FAIL instead.")
                            break
                        comp = storage_addition.dequeue()
                        counts[comp] += 1
            elif factory_component == "Customer":
                if counts[component] >= 1:
                    if result != "SUCCESS":
                        if __name__ != "__main__":
                            return str(len(storage_addition)), component_mentions
                        fail = True
                        print("Error at the line:", line)
                        print("Customer got:", component)
                        print("FAILURE IN CUSTOMER: Wrong reaction - Expected SUCCESS, got FAIL instead.")
                        break
                    else:
                        counts[component] -= 1
                        component_sold_counts[component] += 1
                else:
                    if result != "FAIL":
                        if __name__ != "__main__":
                            return str(len(storage_addition)), component_mentions
                        fail = True
                        print("Error at the line:", line)
                        print("FAILURE IN CUSTOMER: Wrong reaction - Expected FAIL, got SUCCESS instead.")
                        break

        if not fail:
            for line in report:
                if line == "":
                    continue
                components = line.split(":")
                count = int(components[1])
                if not components[0].endswith("Sold"):
                    component = re.search(r"\w+(?=\s(in)\b)", line).group(0)
                    factory_simulation = re.search(r"\w+(?=(:))", line).group(0)
                    if factory_simulation == "line":
                        factory_simulation = "Factory Line"
                        if component == "Card":
                            component = "Graphics Card"
                        check = count == storage_addition.count(component)
                    else:
                        if component == "Card":
                            component = "Graphics Card"
                        check = count == counts[component]
                else:
                    factory_simulation = "Customer"
                    component = re.search(r"\w+(?=\s(Sold)\b)", line).group(0)
                    if component == "Card":
                        component = "Graphics Card"
                    check = count == component_sold_counts[component]
                if not check:
                    print("Error at the Report for component {} at factory division {}".format(
                        factory_component,
                        factory_simulation
                    ))
                    if __name__ != "__main__":
                        return str(len(storage_addition)), component_mentions
                    fail = True
                    break

        if fail:
            print("ERRORS DETECTED IN SYSTEM")
            return line.split(".")[0]
        else:
            print("SUCCESSFUL INTEGRATION TESTING")
            return None


if __name__ == "__main__":
    test_result: str = Test.main("output.txt")
    if test_result is not None:
        file = open("log.txt", "a")
        file.write(str(test_result) + "\n")
        file.close()
