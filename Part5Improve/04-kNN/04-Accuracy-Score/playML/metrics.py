import numpy as np


def accuracy_score(y_true, y_predict):
    '''计算y_true(真正的结果)和y_predict(机器学习预测的结果)之间的准确率'''
    assert y_true.shape[0] == y_predict.shape[0], "the size of y_true must be equal to the size of y_predict"

    return sum(y_true == y_predict) / len(y_true)
