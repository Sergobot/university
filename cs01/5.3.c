/* Copyright 2018, Sergey Popov (me@sergobot.me) */

#include <stdio.h>
#include <locale.h>

int main()
{
    setlocale(LC_CTYPE, "Russian");
    printf("����� ���������� � 5.3! ������� ����� �� 99:\n");
    int n, a, b;
    if (scanf("%d", &n) != 1)
    {
        printf("������ �����.");
        return 1;
    }
    else if (n < 1 || n > 99)
    {
        printf("����� �� �������� � ����������� �����.");
        return 1;
    }
    a = n / 10;
    b = n % 10;
    if (a == 1)
    {
        switch (b)
        {
        case 0: printf("������");       break;
        case 1: printf("�����������");  break;
        case 2: printf("����������");   break;
        case 3: printf("����������");   break;
        case 4: printf("������������"); break;
        case 5: printf("����������");   break;
        case 6: printf("�����������");  break;
        case 7: printf("����������");   break;
        case 8: printf("������������"); break;
        case 9: printf("������������"); break;
        }
        printf(" ������");
    }
    else
    {
        switch (a)
        {
        case 0: break;
        case 2: printf("�������� ");    break;
        case 3: printf("�������� ");    break;
        case 4: printf("����� ");       break;
        case 5: printf("��������� ");   break;
        case 6: printf("���������� ");  break;
        case 7: printf("��������� ");   break;
        case 8: printf("����������� "); break;
        case 9: printf("��������� ");   break;
        }

        switch (b)
        {
        case 0: printf("������");        break;
        case 1: printf("���� �����");    break;
        case 2: printf("��� �����");     break;
        case 3: printf("��� �����");     break;
        case 4: printf("������ �����");  break;
        case 5: printf("���� ������");   break;
        case 6: printf("����� ������");  break;
        case 7: printf("���� ������");   break;
        case 8: printf("������ ������"); break;
        case 9: printf("������ ������"); break;
        }
    }
    printf("\n");

    return 0;
}
