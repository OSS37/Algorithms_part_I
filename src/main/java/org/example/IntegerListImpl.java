package org.example;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int intSize) {
        storage = new Integer[intSize];
    }

    //добавление в конец
    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    //добавление по индексу
    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            storage[size++] = item;
            return item; //ворачиваем item, чтобы прервать метод
        }

        //Если index не равен size , то мы должны сдвинуть все элементы , которые находятся
        // на ячейке, которую мы передали индексом, а также все последующие на одну ячейку вправо
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item; // инжект данного item в данную ячейку по index
        return item; // если все успешно, вернется item, в противном случае выброшено исключение, т.к. не прошло валидацию
    }

    //удаление по item
    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        //найти индекс элемента
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (index != size) {
            //затирание текущего элемента и вычисление кол-во элементов, которые нужно сместить влево
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }

        size--; //обнулили ячейку, не обнулив внутри нее значение
        return item;
    }
    //удаление по index
    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = storage[index];

        if (index != size) {
            //затирание текущего элемента и вычисление кол-во элементов, которые нужно сместить влево
            System.arraycopy(storage, index + 1, storage, index, size - 1- index);
        }

        size--; //обнулили ячейку, не обнулив внутри нее значение
        return item;


    }

    //если метод indexOf вернул какой-либо индекс отличный от дефолтного, значит элемент находится
    //внутри нашей коллекции
    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    // Если элемент не найден, то методы indexOf и lastIndexOf должны вернуть -1,
    // Если элемент найден indexOf ищет с нулевой ячейки, а lastIndexOf с size (последней ячейки)
    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = storage[i];

            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            Integer s = storage[i];

            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }
        //Валидирует индекс (проверить, что индекс попадает в допустимый интервал)
        @Override
        public Integer get ( int index){
        validateIndex(index);
            return storage[index];
        }


        // Вспомогательные методы
        @Override
        public boolean equals (IntegerList otherList){
            return Arrays.equals(this.toArray(), otherList.toArray()); //
        }

        @Override
        public int size () {
            return size;
        }

        @Override
        public boolean isEmpty () {
            return size == 0;
        }

        @Override
        public void clear () {
            size = 0;
        }


        // вернуть копию storage без учета пустых ячеек (незаполненных)
        @Override
        public Integer[] toArray () {
            return Arrays.copyOf(storage, size); //копия массива storage, размера size
        }

        // Проверка (подготовительный этап)
        private void validateItem (Integer item){
            if (item == null) {
                throw new nullItemException();
            }
        }

        private void validateSize () {
            if (size == storage.length) {
                throw new storageIsFullException();
            }
        }

        private void validateIndex ( int index){
            if (index < 0 || index > size) {
                throw new InvalidIndexException();
            }
        }
        private void sort (Integer[] arr) {
            for (int i = 1; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                while (j > 0 && arr[j - 1] >= temp) {
                    arr[j] = arr[j - 1];
                    j --;
                }
                arr[j] = temp;
            }
        }

        private boolean binarySearch(Integer[] arr, Integer item) {
            int min = 0;
            int max = arr.length - 1;

            while (min <= max) {
                int mid = (min + max) / 2;

                if (item == arr[mid]) {
                    return true;
                }

                if (item < arr[mid]) {
                    max = mid - 1;
                } else {
                    min = mid + 1;
                }
            }
            return false;
        }
    }



