def iou_score(bbox1, bbox2):
    xmin = max(bbox1[0], bbox2[0])
    ymin = min(bbox1[1], bbox2[1])

    xmax = min(bbox1[2], bbox2[2])
    ymax = max(bbox1[3], bbox2[3])

    width = max(xmax - xmin, 0)
    height = abs(min(ymax - ymin, 0))

    intersection = width * height
    b1_area = abs(bbox1[3] - bbox1[1]) * abs(bbox1[2] - bbox1[0])
    b2_area = abs(bbox2[3] - bbox2[1]) * abs(bbox2[2] - bbox2[0])

    union = b1_area + b2_area - intersection

    score = intersection / union
    return score


# partial intersect
b1 = [0, 0, 4, -4]
b2 = [2, -2, 6, -6]
print(iou_score(b1, b2))
# Out: 0.14285714285714285

# edge
b1 = [0, 0, 4, -4]
b2 = [4, 0, 6, -4]
print(iou_score(b1, b2))
# Out: 0.0

# complete out
b1 = [0, 0, 4, -4]
b2 = [5, -5, 6, -6]
print(iou_score(b1, b2))
# Out: 0.0

# complete in
b1 = [0, 0, 4, -4]
b2 = [1, -1, 2, -2]
print(iou_score(b1, b2))
# Out: 0.0625
