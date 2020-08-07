package service.impl;

import service.UseStreamDemoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by caoliang on 2017/7/27.
 */
public class UseStreamDemoServiceImpl implements UseStreamDemoService {
    @Override
    public void useStream() {
        stramApiOne();

        filterData();

        getMinMax();
    }

    private void stramApiOne() {
        List<Integer> numbers = Arrays.asList(5, 3, 3, 4, 1);

        int loopSum = 0;
        for (int number : numbers) {
            loopSum += number;
        }

        int streamSum = numbers.stream().reduce(0, (x, y) -> x + y);
        streamSum = numbers.stream().reduce(0, Integer::sum);

        List<Integer> otherNumbers = new ArrayList<>();
        //numbers.stream().forEach(i -> otherNumbers.add(i));

        numbers.stream().forEach(otherNumbers::add);

    }

    private void filterData() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer findResult = numbers.stream().filter(i -> i == 5).findFirst().get();
    }

    private void getMinMax() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int min = numbers.stream().min((i1, i2) -> i1.compareTo(i2)).get();

        int max = numbers.stream().max((i1, i2) -> i1.compareTo(i2)).get();
    }

    public static void main(String[] args) {
        flatMapDemo();
    }

    private static void flatMapDemo() {
        List<ClassDto> classDtoList = new ArrayList<>();
        classDtoList.add(getClassDto("三八班", 6, 13));
        classDtoList.add(getClassDto("一五班", 3, 11));
        classDtoList.add(getClassDto("六六班", 9, 16));

        //获取所有学生的姓名
        //map要用flatMap代替
        String[] nameArr = classDtoList.stream()
                .flatMap(i -> i.getStudentDtoList().stream())
                .map(StudentDto::getName)
                //.collect(Collectors.toList())
                .toArray(String[]::new);

        System.out.println(Arrays.toString(nameArr));
    }

    private static ClassDto getClassDto(String className, int startAge, int endAge) {
        ClassDto classDto = new ClassDto(className, null);

        List<StudentDto> studentDtoList = new ArrayList<>();
        for (int i = startAge; i <= endAge; i++) {
            studentDtoList.add(new StudentDto(i, className + ":姓名" + i));
        }
        classDto.setStudentDtoList(studentDtoList);

        return classDto;
    }


    static class ClassDto {
        String className;
        List<StudentDto> studentDtoList;

        public ClassDto(String className, List<StudentDto> studentDtoList) {
            this.className = className;
            this.studentDtoList = studentDtoList;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public List<StudentDto> getStudentDtoList() {
            return studentDtoList;
        }

        public void setStudentDtoList(List<StudentDto> studentDtoList) {
            this.studentDtoList = studentDtoList;
        }
    }

    static class StudentDto {
        int age;
        String name;

        public StudentDto(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
