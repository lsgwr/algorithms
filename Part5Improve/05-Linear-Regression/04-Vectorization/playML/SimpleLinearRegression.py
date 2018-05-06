import numpy as np


class SimpleLinearRegression:

    def __init__(self):
        """初始化简单线性回归的模型"""
        self.a_ = None
        self.b_ = None

    def fit(self, x_train, y_train):
        """根据训练数据里x_train，y_train训练简单线性回归的模型"""
        assert x_train.ndim == 1, " 简单线性回归只处理有一个特性的训练数据"
        assert len(x_train) == len(y_train), "x_train和y_train的大小必须相等"
        numerator = 0.0  # 分子
        denominator = 0.0  # 分母
        x_mean = np.mean(x_train)
        y_mean = np.mean(y_train)
        # for循环转会为点乘
        numerator = (x_train - x_mean).dot(y_train - y_mean)
        denominator = (x_train - x_mean).dot(x_train - x_mean)
        self.a_ = numerator / denominator
        self.b_ = y_mean - self.a_ * x_mean
        return self

    def predict(self, x_predict):
        """给定待预测数据集x_predict,返回表示x_predict的结果向量"""
        assert x_predict.ndim == 1, "简单线性回归值只解决一个特征值的问题"
        assert self.a_ is not None and self.b_ is not None, "must fit before predict"

        return [self._predict(x) for x in x_predict]

    def _predict(self, x):
        """返回单个待测数据预测的结果值"""
        return self.a_ * x + self.b_

    def __repr__(self):
        return "SimpleLinearRegression()"
